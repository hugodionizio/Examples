# Definição do número do exemplo
EXAMPLE="${0##*/}"
pos=`expr ${#EXAMPLE} - 5`
variavel=${EXAMPLE: $pos: 2}
if (echo $variavel | egrep '[^0-9]' &> /dev/null)
	then
	# variável não numérica
	pos=`expr ${#EXAMPLE} - 4`
	EXAMPLE=${EXAMPLE: $pos: 1}
else
	EXAMPLE=$variavel
fi

echo "Implementando Exemplo $EXAMPLE"

# Criação da Pasta de binários "bin"
mkdir bin

# Compilação e verificação da biblioteca em C
gcc -fPIC -c src/example$EXAMPLE.c
gcc -shared -o bin/libexample$EXAMPLE.so example$EXAMPLE.o
rm example$EXAMPLE.o
nm bin/libexample$EXAMPLE.so

# Importação da biblioteca jna.jar
cp src/jna.jar bin/jna.jar
cd bin/
jar xvf jna.jar com/
rm jna.jar
cd ..

# Compilação do projeto em Java
javac -encoding UTF-8 -classpath src/jna.jar src/Example$EXAMPLE.java -d bin/
cp -r src/META-INF bin/META-INF
cd bin
jar cvfm Example$EXAMPLE.jar META-INF/MANIFEST.MF Example*.class com libexample$EXAMPLE.so

# Execução do projeto em jar
java -jar Example$EXAMPLE.jar

cd ..
rm src/example.c
rm src/CLibrary.java
