package controllers

import (
	"cart-app/models"
	"encoding/json"
	"net/http"

	"github.com/gin-gonic/gin"
)

type CartDto struct {
	UserId    string `json:"userid"`
	ProductId string `json:"productid"`
	Quantity  int    `json:"quantity"`
}

func FindByUserId(c *gin.Context) {
	userid := c.Param("userid")
	vo, err := models.DB.HGetAll(userid).Result()

	dtoArray := []CartDto{}

	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"data": dtoArray})
	}

	for _, e := range vo {
		dto := CartDto{}
		json.Unmarshal([]byte(e), &dto)
		dtoArray = append(dtoArray, dto)
	}

	c.JSON(http.StatusOK, gin.H{"data": dtoArray})
}

func Update(c *gin.Context) {
	dto := CartDto{}
	c.ShouldBindJSON(&dto)
	cartProduct := models.CartProduct(dto)
	models.SAddCartProduct(cartProduct)
}
