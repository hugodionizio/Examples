# Definição do número do exemplo
EXAMPLE="${0##*/}"
pos=`expr ${#EXAMPLE} - 5`
variavel=${EXAMPLE: $pos: 2}
if (echo $variavel | egrep '[^0-9]' &> /dev/null)
	then
	# variável não numérica
	pos=`expr ${#EXAMPLE} - 4`
	EXAMPLE=${EXAMPLE: $pos: 1}

	if (echo $EXAMPLE | egrep '[^0-9]' &> /dev/null)
		then
		# variável não numérica
		echo "Exemplo número $EXAMPLE?"
		echo "Faltou enumerar este Script..."
		exit
	fi
fi

# Adição de numeração nas referências de ordem do projeto
sed -i "s/xample/xample$EXAMPLE/g" .cg_conf
sed -i "s/xample/xample$EXAMPLE/g" src/META-INF/MANIFEST.MF
sed -i "s/xample/xample$EXAMPLE/g" src/Example.java

# Renomear compilador e montador
mv Example.sh Example$EXAMPLE.sh

# Realocamento dos arquivos do projeto para a pasta "src"
mv CLibrary.java src/CLibrary.java
mv example$EXAMPLE.c src/example$EXAMPLE.c

# Substituir 1ª linha da classe em Java pela 1ª linha do arquivo em C
head -n 1 src/example$EXAMPLE.c > tmp.java
NUM=`wc -l src/Example.java`
NUMTMP="${NUM#* }"
LIM=`expr ${#NUM} - ${#NUMTMP} - 1`
NUMTOTAL=${NUM: 0: $LIM}
NUMBODY=`expr $NUMTOTAL - 1`
tail -n $NUMBODY src/Example.java >> tmp.java
rm src/Example.java
mv tmp.java src/Example$EXAMPLE.java
