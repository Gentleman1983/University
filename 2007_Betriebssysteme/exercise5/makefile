all: race_demo race_demo_schedyield peterson_demo peterson_demo_modular

race_demo: race_demo.c
	gcc -O3 -Wall -lpthread -o race_demo race_demo.c

race_demo_schedyield: race_demo_schedyield.c
	gcc -O3 -Wall -lpthread -o race_demo_schedyield race_demo_schedyield.c

peterson_demo: peterson_demo.c
	gcc -O3 -Wall -lpthread -o peterson_demo peterson_demo.c

peterson_demo_modular: peterson_demo_modular.c
	gcc -O3 -Wall -lpthread -o peterson_demo_modular peterson_demo_modular.c

clean:
	rm -f race_demo race_demo_schedyield peterson_demo peterson_demo_modular
