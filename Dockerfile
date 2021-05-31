#Esse arquivo descreve como vamos montar a nossa imagem.
#Ele fica na raiz do projeto
#Ele contém instruções para gerar imagem e para dizer a partir de qual imagem vamos gerar a nossa imagem

#Essa imagem será gerada a partir da imagem do openjdk:15 que possui tudo que agente precisa para
#executar a nossa aplicação, inclusive o Java 15
FROM openjdk:15
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
#Ponto de entrada significa: quando o docker for executar a nossa aplicação, qual será a classe
# que ele vai chamar para ser executada ? tenho que colocar o endereço da minha classe principal
ENTRYPOINT ["java","-cp","app:app/lib/*","br.com.instadev.aws_instadev.AwsInstadevApplication"]