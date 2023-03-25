package models

import (
	"encoding/json"

	"github.com/go-redis/redis"
)

var DB *redis.Client

func ConnectDatabase() {
	client := redis.NewClient(&redis.Options{
		Addr:     "redis:6379",
		Password: "", // no password set
		DB:       0,  // use default DB
		PoolSize: 100,
	})

	_, err := client.Ping().Result()

	if err != nil {
		panic(err)
	}

	DB = client
}

func InitData() {
	const userid = "123456789"
	cartProduct1 := CartProduct{
		UserId:    userid,
		ProductId: "999999999",
		Quantity:  99,
	}
	cartProduct2 := CartProduct{
		UserId:    userid,
		ProductId: "888888888",
		Quantity:  10,
	}
	DB.Del(userid)
	SAddCartProduct(cartProduct1)
	SAddCartProduct(cartProduct2)
}

func SAddCartProduct(cartProduct CartProduct) {
	json, err := json.Marshal(cartProduct)

	if err != nil {
		panic(err)
	}

	// addMap := make([]map[string]interface{}, 0)
	// addMap = append(addMap, map[string]interface{}{
	// 	cartProduct.ProductId: string(json),
	// })

	_, resultErr := DB.HMSet(cartProduct.UserId, map[string]interface{}{
		cartProduct.ProductId: string(json),
	}).Result()

	if resultErr != nil {
		panic(resultErr)
	}
}
