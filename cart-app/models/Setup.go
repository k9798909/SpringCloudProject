package models

import (
	"encoding/json"

	"github.com/go-redis/redis"
)

var DB *redis.Client

func ConnectDatabase() {
	client := redis.NewClient(&redis.Options{
		Addr:     "redis:6379",
		Password: "",
		DB:       0,
		PoolSize: 100,
	})

	_, err := client.Ping().Result()

	if err != nil {
		panic(err)
	}

	DB = client
}

func SAddCartProduct(cartProduct CartProduct) error {
	json, err := json.Marshal(cartProduct)

	if err != nil {
		return err
	}

	_, resultErr := DB.HMSet(cartProduct.Username, map[string]interface{}{
		cartProduct.ProductId: string(json),
	}).Result()

	if resultErr != nil {
		return resultErr
	}

	return nil
}

func HDelCartProduct(username string, productId string) {
	DB.HDel(username, productId)
}
