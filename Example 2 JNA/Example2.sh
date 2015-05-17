# Criação da Pasta de binários "bin"
mkdir bin

# Compilação e verificação da biblioteca em C
gcc -fPIC -c src/example2.c
gcc -shared -o bin/libexample2.so example2.o
rm example2.o
nm bin/libexample2.so

# Importação da biblioteca jna.jar
cp src/jna.jar bin/jna.jar
cd bin/
jar xvf jna.jar com/
rm jna.jar
cd ..

# Compilação do projeto em Java
javac -encoding UTF-8 -classpath src/jna.jar src/Example2.java -d bin/
cp -r src/META-INF bin/META-INF
cd bin
jar cvfm Example2.jar META-INF/MANIFEST.MF Example2.class Example2\$Example2.class com libexample2.so

# Execução do projeto em jar
java -jar Example2.jar
