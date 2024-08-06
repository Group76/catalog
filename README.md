# Catalog
Projeto para lidar com a criação dos produtos, seja eles do tipo lanche, acompanhamento, bebidas ou sobremesa.

### Arquitetura

![Diagram](https://github.com/Group76/catalog/blob/main/docs/catalog.drawio.png)

### SAGA Pattern

A pattern escolhida foi a coreografada para não ter um serviço fazendo tudo e pelo risco que tem de ele parar e o processo inteiro parar também, fora níveis de complexidade para modificações, pois quanto mais responsabilidade maior será o desafio para futuras modificações.
A coreografia foi feita utilizando o SNS, sendo assim posta as mensagens necessárias nele e lê quem tem o interesse na informação, sendo possível efetuar ações que ache necessário.

### Como rodar
Necessário subir a infraestrutura do projeto [AWS Live](https://github.com/Group76/aws-live) e adicionar no Parameter Store a configuração **/config/catalog-api_prod/mongoUri** com o valor da Uri do Mongo.

### AWASP ZAP
Antes: <https://github.com/Group76/catalog/tree/main/docs/awasp-zap-antes>

Depois: <https://github.com/Group76/catalog/tree/main/docs/awasp-zap-depois>

### SWAGGER
Collection: <https://github.com/Group76/catalog/blob/main/docs/api-docs.json>

![Swagger](https://github.com/Group76/catalog/blob/main/docs/swagger-ui_index.html.png)
