
# Fabrick Proyect

Progetto Fabrick corrispondente ad un test tecnico di Aizoon Group
## API Reference

```http
  localhost:8080
```
```http
  /localhost:8080/swagger-ui.html
```


#### Lettura Saldo

```http
  GET /api/v1/fabrick/saldo
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `accountId` | `string` | **Required**. Il tuo ID account |


#### Bonifico

```http
  POST /api/v1/fabrick/bonifico
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `accountId` | `string` | **Required**. Il tuo ID account |
| `receiverName` | `string` | **Required**. Il beneficiario del bonifico |
| `description` | `string` | **Required**. Descrizione del bonifico |
| `currency` | `string` | **Required**. Valuta |
| `amount` | `Long` | **Required**. Quantità |
| `executionDate` | `string` | **Required**. Data di esecuzione |



#### Lettura Transazioni

```http
  GET /api/v1/fabrick/transazioni
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `accountId` | `string` | **Required**. Il tuo ID account |
| `fromAccountingDate` | `string` | **Required**. Dalla data contabile |
| `toAccountingDate` | `string` | **Required**. Alla data contabile |



## Running Tests

I test sono stati effettuati in Postman, di seguito allego il BulkEdit da copiare e poter fare il test

#### Lettura Saldo

- accountId:14537780


#### Bonifico

- accountId:14537780
- receiverName:John Doe
- description:Payment invoice 75/2017
- currency:EUR
- amount:800
- executionDate:2019-04-01


#### Lettura Transazioni

- accountId:14537780
- fromAccountingDate:2019-04-01
- toAccountingDate:2020-04-01
