# Salesman CRUD API

## Requisitos

Você precisa criar uma API que faça um CRUD completo de Vendedores considerando os seguintes dados:

Matrícula
Obrigatório e único
Deve terminar com os seguintes caracteres:
- OUT
- CLT
- PJ
Exemplo:
98767367-OUT
Nome
Obrigatório
Data de Nascimento
Opcional
Validar o formato Data
CPF ou CNPJ
Obrigatório
O valor precisa ser válido
E-mail
Obrigatório
Formato validado
Tipo de Contratação
Obrigatório
Valores possíveis:
Outsourcing (terceirizado)
CLT
Pessoa Jurídica
Filial
Obrigatório
Os valores da filial serão retornados através de uma API (que você deve mockar) com os seguintes dados: ID, Nome, CNPJ,
Cidade, UF, Tipo, Ativo, Data Cadastro, Última Atualização

REGRAS DE NEGÓCIO:

A matrícula deve ser um sequencial gerado automaticamente
O cadastro deve ser feito de forma assíncrona observando-se os seguintes pontos:
A API deve dar um retorno de imediato de recebimento dos dados
Em um segundo momento o usuário deve ter alguma forma de receber os dados cadastrados do Vendedor ou alguma explicação
sobre erro, caso tenha ocorrido
Atentar para o documento a ser registrado pelo Vendedor. Pessoas Jurídicas utilizam CNPJ e os demais CPF

COMO ENTREGAR:

Em um repositório privado do github com permissão de leitura para os seguintes usuários: fbiopereira,
guilhermeponciano-viahub, JeanKelvin, bruno-gomes
Responda esse e-mail para todos com o link do repositório
Assim que você enviar o repositório, avaliaremos seu código e marcaremos um papo com outros desenvolvedores para que
você explique o que você entregou

O QUE SERÁ AVALIADO:

A qualidade da sua solução como um todo, incluindo organização de código, testes, tecnologias e frameworks utilizados
Observabilidade da API