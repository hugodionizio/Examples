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

# Gera os cabeçalhos do código em C e da classe em Java
#	Projeto
echo "// Projeto : JNA Example #$EXAMPLE" > tmp.c
cat tmp.c > tmp.java

#	Algorimto e Classe
echo "// Algoritmo: example$EXAMPLE.c" >> tmp.c
echo "// Classe: Example$EXAMPLE.java" >> tmp.java

#	Escopo
FUNCAO=`cat -n src/example$EXAMPLE.c | grep 27`
FUNCAO="// Função : "${FUNCAO: 22}
echo $FUNCAO > tmp.escopo
echo "// Autor(es) : Hugo Dionizio Santos" >> tmp.escopo
echo "// Data : "`date` >> tmp.escopo

#	Concatenando cabeçalhos
cat tmp.escopo >> tmp.c
cat tmp.escopo >> tmp.java
rm tmp.escopo

# Concatenar cabeçalhos com corpo dos códigos
#	Concatenando documentação do código em C
echo "// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções" >> tmp.c
#	Concatenando funções
NUM=`wc -l src/example$EXAMPLE.c`
NUMTMP="${NUM#* }"
LIM=`expr ${#NUM} - ${#NUMTMP} - 1`
NUMTOTAL=${NUM: 0: $LIM}
NUMBODY=`expr $NUMTOTAL - 30`
tail -n $NUMBODY src/example$EXAMPLE.c >> tmp.c
echo "// Fim do algoritmo" >> tmp.c
rm src/example$EXAMPLE.c
mv tmp.c src/example$EXAMPLE.c

#	Concatenando corpo da classe em Java
NUM=`wc -l src/Example.java`
NUMTMP="${NUM#* }"
LIM=`expr ${#NUM} - ${#NUMTMP} - 1`
NUMTOTAL=${NUM: 0: $LIM}
NUMBODY=`expr $NUMTOTAL - 1`
tail -n $NUMBODY src/Example.java >> tmp.java
echo "// Fim da classe" >> tmp.java
rm src/Example.java
mv tmp.java src/Example$EXAMPLE.java
