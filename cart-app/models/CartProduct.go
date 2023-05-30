package models

type CartProduct struct {
	Username  string `json:"username"`
	ProductId string `json:"productId"`
	Quantity  int    `json:"quantity"`
}
