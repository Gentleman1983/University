.data
		txt1:		.asciiz	"Obere Schranke N: "
		txt2:		.asciiz	"\nBetrachte nicht gestrichene Zahl "
		txt3:		.asciiz	"\nStreiche vielfaches "
		txt4:		.asciiz	"\nAnzahl der benötigten Bytes: "
		txt5:		.asciiz	"\nPrimzahl: "
		schranke:	.word		0
		byte:		.byte		16
		mask:		.byte		128, 64, 32, 16, 8, 4, 2, 1
# Bitmask ist ein Array, das binär der Darstellung 10000000,01000000,...,00000001 entspricht
.text

# 1. Einlesen der oberen Schranke
main:		li 		$v0, 4		# Aus- und Eingabe der oberen Schranke
		la		$a0, txt1		#
		syscall				#	
		li		$v0, 5		#
		syscall				#
		sw		$v0, schranke	#
# 2. Festlegen der Byte-Größe
		lw		$t0, schranke		# Bytegröße bestimmen
		lb		$t1, byte			#
		divu		$t0, $t0, $t1		# N : 16 + 1
		addi		$t0, 1			#
		li		$v0, 4			# Ausgabe der Bytegröße
		la		$a0, txt4			#
		syscall					#
		li		$v0, 1			#
		move		$a0, $t0			#
		syscall					#
# 3. Sieb auf dem Stack speichern
# $t0 = Siebgröße in Bytes
		li		$t1, 0
for:		bge		$t1, $t0, endfor	
		addi		$sp, $sp, -1		# Zeiger eins vorziehen
		li		$t2, 0			# und zur Sicherheit Nullen
		sb		$t2, 1($sp)
		addi		$t1, 1		
		b		for
endfor:
# 4. Vom Stack lesen, richtiges Byte und Stelle finden
# Zuerst eine äußere Schleife, die bei i=3 anfängt und bis zur angegebenen oberen Schranke läuft
# Entspricht der for-Schleife in Hochsprache
# $t0 = Zähler der äußeren Schleife, gültig bis Ende Abschnitt 7.
		li		$t0, 1			
		lw		$t7, schranke		# $t7 = obere Schranke
while:	addi		$t0, $t0, 2
		bgt		$t0, $t7, endwhile	# Sprung zu 8. wenn Grenze erreicht
# An welcher Position im Stack befindet sich die gesuchte Zahl?
		lb		$t1, byte
		divu		$t0, $t1			# Zähler i : 16
		mfhi		$t2				# Rest
		mflo		$t1				# Ergebnis
		srl		$t2, $t2, 1			# Teile Rest durch 2
# $t1 = Byte, in dem die Zahl i steckt. 
# $t2 = Stelle in dem Byte, die den Status der Zahl i repräsentiert
#
# Stackpointer bis zum gesuchten Byte vorrücken lassen
		add		$sp, $sp, $t1
# Byte vom Stack lesen und mit der Maske der gesuchten Stelle vergleichen (if-Schleife).
# Wenn die Zahl schon gestrichen ist, müssen die Vielfachen nicht mehr gestrichen werden
# Wenn die Zahl noch nicht gestrichen ist, ist sie eine Primzahl und das Programm 
# springt zu "prozess" um alle Vielfachen der Primzahl zu streichen
		lb		$t3, 1($sp)
		lb		$t4, mask($t2)		
		and		$t5, $t3, $t4		# $t4 = Byte AND MASK(stelle)
		sub		$sp, $sp, $t1		# Stackpointer zurückholen
		bne		$t4, $t5, prozess		# Ergebnis und Maske ungleich?
		b		while	
# 5. Streichen aller Vielfachen bis zur oberen Schranke
# Entspricht innerster for-Schleife
prozess:	li		$v0, 4			# Debug-Ausgabe: "Betrachte nichtgestrichene Zahl"
		la		$a0, txt2			#
		syscall					#
		li		$v0, 1			#
		move		$a0, $t0			#
		syscall
# Streichen der Vielfachen und zurückschreiben auf den Stack
		mul		$t1, $t0, $t0		# Anfang der for-Schleife mit j=i*i
		bgt 		$t1, $t7, endwhile	# Abbruch, falls j jetzt schon größer als N
# 6. An welcher Position im Stack befindet sich die gesuchte Zahl j?
loop:		lb		$t2, byte
		divu		$t1, $t2			# Zähler j : 16
		mfhi		$t3				# Rest
		mflo		$t2				# Ergebnis
		srl		$t3, $t3, 1			# Teile Rest durch 2
		li		$v0, 4			# Debug-Ausgabe: "Streiche vielfaches"
		la		$a0, txt3			#
		syscall					#
		li		$v0, 1			#
		move		$a0, $t1			#
		syscall
# $t0 = Zähler der obersten Schleife
# $t1 = Zähler der inneren Streichschleife
# $t2 = Byte des zu streichenden Bits
# $t3 = Stelle des zu streichenden Bits
# 7. Stackpointer bis zum gesuchten Byte vorrücken lassen
		add		$sp, $sp, $t2
		lb		$t4, 1($sp)
		lb		$t5, mask($t3)
# Mit dem or-Operator wird der Status der Zahl j auf 1(=gestrichen) gesetzt
		or		$t4, $t4, $t5
		sb		$t4, 1($sp)		# Zurückspeichern auf den Stack
		sub		$sp, $sp, $t2	# Stackpointer zurückholen
# Zähler j um 2*i erhöhen (Gerade zahlen können ignoriert werden, darum gleich 2 * i statt nur + i)
# Wenn j größer als N, innerste Schleife abbrechen und i erhöhen, d.h. die vielfachen der nächsten Primzahl streichen
		sll		$t6, $t0, 1 
		add		$t1, $t1, $t6
		bgt		$t1, $t7, while		# Sprung zu 4. wenn Grenze erreicht
		b		loop				# Wiederhole streichen ab 6. wenn nicht
endwhile:
# 8. Ausgabe der nichtgestrichenen Zahlen
# Der Ablauf ähnelt der Suche. Alle (ungeraden) Zahlen werden durchlaufen und im Stack
# nachgeschaut, ob sie gestrichen sind. Wenn dies nicht der Fall ist, wird die Zahl ausgegeben
# Ist N erreicht, sind alle Primzahlen gefunden und ausgegeben
		li		$t0, 1			# $t0 = Zähler der äußeren Schleife
		lw		$t7, schranke		# $t7 = obere Schranke
outloop:	addi		$t0, $t0, 2
		bgt		$t0, $t7, out
# An welcher Position im Stack befindet sich die gesuchte Zahl?
		lb		$t1, byte
		divu		$t0, $t1			# Zähler i : 16
		mfhi		$t2				# Rest
		mflo		$t1				# Ergebnis
		srl		$t2, $t2, 1			# Teile Rest durch 2
# $t1 = Byte, in dem die Zahl steckt. 
# $t2 = Stelle in dem Byte
#
# Stackpointer bis zum gesuchten Byte vorrücken lassen
		add		$sp, $sp, $t1
# Byte vom Stack lesen und mit der Maske der gesuchten Stelle vergleichen
		lb		$t3, 1($sp)
		lb		$t4, mask($t2)
		and		$t5, $t3, $t4		# $t4 = Byte AND MASK(stelle)
		###### Stackpointer zurückholen
		sub		$sp, $sp, $t1
		bne		$t4, $t5, ausgabe		# Ergebnis und Maske gleich?
		b		outloop	
ausgabe:	li		$v0, 4		# Debug-Ausgabe: "Streiche vielfaches"
		la		$a0, txt5		#
		syscall				#
		li		$v0, 1		#
		move		$a0, $t0		#
		syscall
		b		outloop
out:		li		$v0, 10
		syscall	
