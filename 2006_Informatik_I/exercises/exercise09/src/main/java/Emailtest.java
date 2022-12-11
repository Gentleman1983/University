public class Emailtest 
{
    public static void main(String[] args) 
    {
        String Absender = "Christian@Otto-Uslar.de";
        String Empfaenger[] = {"Tobias@Otto-Uslar.de", "A1exhartmann@aol.com", "kaiuwe_ricke@telekom.de"};
        String Betreff = "Kennt ihr denn schon Douglas Adams?";
        String Inhalt = "Kennt ihr denn den Anhalter? Und noch nicht einmal Douglas Adams? Das ist " +
                "eine wahre Kulturlücke. Dann weißt du ja noch nicht einmal was es mit der 42 auf sich hat!";
        
        Email meineMail = new Email(Absender, Empfaenger, Betreff, Inhalt);
        meineMail.print();
        meineMail.sort();
        meineMail.print();
    }
    
}
