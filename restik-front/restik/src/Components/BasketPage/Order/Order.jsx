import React from "react";
import {useNavigate} from "react-router-dom";
import { useTranslation } from "react-i18next";
import { BASE_URL, STORAGE_ITEM } from "../../../constants/constants";
import "./Order.css";

export const Order = () => {
    const {t} = useTranslation();
    const navigator = useNavigate();
    const user = JSON.parse(localStorage.getItem(STORAGE_ITEM));
    const makeOrder= async(e)=> {
        e.preventDefault();
        const url = BASE_URL+`basket/order/${user.id}`
        const response = await fetch(url, {
            method: 'post',
        })
        if(response.status === 200){
            navigator("/success")
        }
    }
    return (
        <>
            <section class="order">
                <div class="container">
                    <div class="order-header">
                        <h3 class="order-title">{t("order-make-order")}</h3>
                    </div>
                    <form method="post" onSubmit={makeOrder} >
                        <div class="form-wrap">
                            <div class="input-container">
                                <label for="name">{t("order-name")}</label><br />
                                <input class="input" type="text" name="name" id="name" required />
                            </div>
                            <div class="input-container">
                                <label for="surname">{t("order-surname")}</label><br />
                                <input class="input" type="text" name="surname" id="surname" required />
                            </div>
                            <div class="input-container">
                                <label for="street">{t("order-street")}</label><br />
                                <input class="input" type="text" name="street" id="street" required />
                            </div>
                            <div class="input-container">
                                <label for="home">{t("order-building")}</label><br />
                                <input class="input" type="text" name="home" id="home" required />
                            </div>
                            <div class="input-container">
                                <label for="flat">{t("order-flat")}</label><br />
                                <input class="input" type="text" name="flat" id="flat" required />
                            </div>
                            <button class="order-btn" type="submit">{t("order-make-order-btn")}</button>
                            {/* <div id="information" class="order-info">
                                <p>Заказ успешно оформлен</p>
                            </div> */}
                        </div>
                    </form>
                </div>
            </section>
        </>
    )
}