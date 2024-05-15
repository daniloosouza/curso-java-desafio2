# curso-java-desafio2

Desafio final do Programa **Renew your Career (Java & Spring Boot)**.

## 📄 Introdução
### Detalhes da aplicação
API para gerenciamento de contas por parte da instituição financeira.
Esta API possui as seguintes operações:
#### Cadastro de conta bancária
Cadastro de contas bacárias contendo as seguintes informações:
1. Número
2. Agência
3. Titular
4. Valor de saldo total
5. Valor de limite de transações diárias
6. Tipo da conta: Conta corrente, Conta Poupança e Conta salário

#### Informações da conta
1. Lista com informações de todas as contas bancárias existentes no sistema
2. Informação de uma conta específuca

#### Depósito
Depósito de valor em conta

#### Saque
Saque de valor de conta

#### Alteração de limite
Alteração de limite de transação diária

#### Transferência
Realização de transferências bancárias informando a conta de origem e destino

#### Histórico de transações
Listagem de histórico de transações

Os detalhes e contrato das operações estão contidas no arquivo swagger.yaml localizado em: **src/main/resources/swagger.yaml**

## 🚀 Começando...

Essas instruções permitirão que você obtenha uma cópia do projeto em sua máquina local para fins de desenvolvimento e teste.

### 📋 Pré-requisitos
- **[Git](https://git-scm.com/downloads)**
- **[Java 17 ou superior](https://www.oracle.com/br/java/technologies/javase/jdk17-archive-downloads.html)**
- **IDE compatível com a linguagem JAVA**
- **MongoDB**

### 🔧 Instalação
1. Realize o clone do projeto através do Github:
```
git clone https://github.com/daniloosouza/curso-java-desafio2.git
```
2. Importe o projeto na IDE de sua preferência:

## ⚙️ Execução

1. Adicione a configuração para o MongoDB no arquivo: **src/main/resources/application.properties** alterando o valor da propriedade abaixo: 
   1. spring.data.mongodb.uri
2. Execute a classe **ApisApplication**, contida em:
   ```src/main/java/com/desafio2/apis/ApisApplication.java```
3. Pronto! A API está pronta para consumo.

## ✒️ Autores
* **Danilo de Oliveira** - **[github](https://github.com/daniloosouza)**

---