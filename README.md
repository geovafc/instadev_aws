# DOCKER
### Subir imagem para o docker hub
Para conseguir subir uma imagem para o docker hub usando o linux, execute os seguintes passos:

####Realize o login :  
    docker login docker.io

####Execute o comando:

    ./gradlew docker dockerPush"AQUI VOCÊ INFORMA A TAG E REMOVE AS ASPAS"

### Fazer build da imagem 
    docker build -t instadev .

### Executar imagem do docker com a aplicação    
    docker run -p 8080:8080 instadev
        
        *onde instadev é o nome da minha imagem