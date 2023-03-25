package models

type CartProduct struct {
	UserId    string `json:"userid"`
	ProductId string `json:"productid"`
	Quantity  int    `json:"quantity"`
}
