# carteiradigital

<h3>Passo a passo para executar projeto localmente: </h3>

- Baixe o repositório e abra-o em seu editor.

- exite um arquivo docker-compose.yml no repositorio github do projeto. Abra o seu terminal e navegue até a pasta onde o arquivo docker está localizado e inicialize utilizando o comando "docker-compose up -d". Essa etapa vai fazer com que seja inicializado o postgres (persistencia dos dados) e rabbitmq utilização do comando assíncrono).

-Inicialize o projeto.

<h4>Testes com Swagger</h4>

para que possamos testar aplicação, utilizaremos a ferramenta Swagger.ui. Com a aplicação rodando localmente, abra em seu browser o swagger através 
do link: http://localhost:8081/swagger-ui.html#/

essa será a interface graficar que você utilizará

![swagger](https://user-images.githubusercontent.com/69025247/142252742-d1e51baf-c743-438b-a05c-ad41280db91f.jpeg)

Através da ferramenta de versionamento de dados FlyWay,já serão inicializados os dados:

<h2>TB_CARTEIRA</h2>

![tabela_carteira](https://user-images.githubusercontent.com/69025247/142253971-ace59fd4-7f8e-46f1-9c7d-f91fbc750f51.jpeg)

<h2>TB_USUARIO</h2>

![tabela_usuario](https://user-images.githubusercontent.com/69025247/142254570-f6cec17c-f4bb-4616-a6f3-a08f0e850617.jpeg)

<h4>obs.: Utilize os dados acima para testes no swagger</h4>

note que ao selecionar qual o endpoint que será testado, o swagger já irá informar quais os dados que ele esta esperando para a operação:

![sacar_endpoint](https://user-images.githubusercontent.com/69025247/142255694-ccabb3c1-9420-491a-8ab1-342731278883.jpeg)

clique no botão "try it out" substitua os dados e depois clique em "execute", conforme imagem abaixo (Como exemplo, usarei o usuário Thiago Silveira, conforme
tabela usuário):

![execute](https://user-images.githubusercontent.com/69025247/142256312-fd2e80d5-e67e-4485-8ecb-e81a4fedca76.jpeg)

Após executar o comando, você poderá utilizar o endpoint "BuscaSaldo" para poder verificar o novo saldo do usuário.

<h4>Obs.:Além do endpoint de buscar saldo, o projeto conta com o endpoint get que retorna um histórico de atividades para cada usuário, basta informar o id (ENDPOINT->Historico)</h4>

<h2>Menságem Assíncrona</h2>

- Para testar menssagens assíncontra, utilizaremos o rabbitmq. Uma vez startado o docker-compose-yml note que foi iniciado uma imagem do rabbitmq que esta sendo executado na porta 15672. Abra o browser no link: http://localhost:15672/#/queues

<h5>Cenário da messangem assíncrona</h5>
- O cenário para essa mensagem é a transferencia entre bancos diferentes.

Note que ao abrir o link do rabbitmq você estará com as informações da imagem abaixo:

![rabbitmq](https://user-images.githubusercontent.com/69025247/142257927-7c19043e-03bc-4019-aeb8-1fd634bd2f91.jpeg)

Clique em "outro banco" e você será redirecionado para outra aba para que possa ser feito a postagem de uma nova mensagem.

![postar_mensagem](https://user-images.githubusercontent.com/69025247/142258532-cf7fccce-2d00-42c2-a02a-cf828ac63434.jpeg)

Utilize o modelo abaixo como exemplo, troque para as informações desejadas conforme dados persistidos no banco de dados
	
  {
			"nomeTransferidor":"Nome de que esta tranferido",
			"numeroContaRecebedor":"troque pelo numero da conta do usuário recebedor",
			"valorTransferido":"troque pelo valor da transferencia"
		}
    
![teste _rabbitmq](https://user-images.githubusercontent.com/69025247/142259099-89c5138d-5aa9-4c99-b1e9-981abd293138.jpeg)

Depois disso, cheque o novo saldo do usuário que recebeu a transferência e o histórico para o mesmo.
