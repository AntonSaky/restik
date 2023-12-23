import React, { useEffect, useState } from "react";
import "./BasketPage.css"
import { Order } from "./Order/Order";
import { BASE_URL, STORAGE_ITEM } from "../../constants/constants";
import { useTranslation } from "react-i18next";
import { Navbar } from "../Navbar/Navbar";
import { Footer } from "../Footer/Footer";

export const BasketPage = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [dishesInfo, setDishesInfo] = useState([]);
    const user = JSON.parse(localStorage.getItem(STORAGE_ITEM));
    const { t } = useTranslation();

    useEffect(() => {
        const getDishesInBasket = async () => {
            const url = BASE_URL + "basket/" + user.id;
            const response = await fetch(url);
            if (response.status === 200) {
                const dishes = await response.json();
                setIsLoading(false);
                setDishesInfo(dishes);
            }
        }
        try {
            if (user) {
                getDishesInBasket();
            }

        } catch (error) {
            console.log(error.message);
        }
    }, [])

    const getDishIdFromUrl = (url = '') => {
        const ind = url.lastIndexOf('/');
        const id = url.slice(ind + 1);
        return +id;
    }
    const increment = async (url) => {
        const response = await fetch(url, { method: 'post', });
        if (response.status === 200) {
            const dish_id = getDishIdFromUrl(url);
            const tempDishes = dishesInfo.map(dishInfo => {
                if (dishInfo.dish.dish_id === dish_id) {
                    dishInfo.amount++;
                }
                return dishInfo;
            })
            setDishesInfo(tempDishes);
        }
    }
    const decrement = async (url) => {
        const response = await fetch(url, { method: 'post' });
        if (response.status === 200) {
            const dish_id = getDishIdFromUrl(url);
            let tempDishes = dishesInfo.map(dishInfo => {
                if (dishInfo.dish.dish_id === dish_id) {
                    dishInfo.amount--;

                }
                return dishInfo;
            })
            tempDishes = tempDishes.filter(d => d.amount !== 0);
            setDishesInfo(tempDishes);
        }
    }
    return (
        <>
            <Navbar />
            <section class="basket">
                <div class="container">
                    {dishesInfo.length === 0 && user ?
                        <p class='empty-basket'>{t("basket-empty")}</p> :

                        dishesInfo.map(dishInfo => {
                            return (
                                <>
                                    <div class='basket-item'>
                                        <div class='basket-item-container'>
                                            <div class='basket-item-img'>
                                                <img src={dishInfo.dish.img} alt={dishInfo.dish.name} />
                                            </div>
                                            <div class='basket-item-info'>
                                                <h3 class='basket-item-title'>{dishInfo.dish.name}</h3>
                                                <div class='basket-item-price-wrap'>
                                                    <p class='basket-item-price'>
                                                        {t("basket-price")} <span>{dishInfo.dish.price * dishInfo.amount}</span> {t("currency")}</p>
                                                    <div class='basket-item-amount'>

                                                        <button class="inc" onClick={() => increment(`http://localhost:8080/basket/${user.id}/increment/${dishInfo.dish.dish_id}`)}>+</button>

                                                        <input type="number" name="amount" id="amount" class="amount-input" value={dishInfo.amount} />

                                                        <button class="dec" onClick={() => decrement(`http://localhost:8080/basket/${user.id}/decrement/${dishInfo.dish.dish_id}`)}>-</button>


                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <hr class='line' />
                                </>
                            )
                        })
                    }
                    {
                        dishesInfo.length !== 0 ? <Order /> : null
                    }

                    {
                        user ? null : <p class='empty-basket'>{t("basket-not-auth")}</p>
                    }

                </div>
            </section>
            <Footer />
        </>
    )
}