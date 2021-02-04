create table CEP(
  ID int not null auto_increment,
  CODIGO_LOJA varchar(50),
  FAIXA_INICIO  INT unique,
  FAIXA_FIM INT unique,
  primary key(ID)
);