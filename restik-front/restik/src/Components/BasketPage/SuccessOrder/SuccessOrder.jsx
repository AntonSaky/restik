import React from "react";
import {Link} from "react-router-dom";
import { useTranslation } from "react-i18next";
import "./SuccessOrder.css"


export const SuccessOrder = () => {
    const {t} = useTranslation();
    return (
        <div class="success-wrapper">
            <div class="emoji">╰(*°▽°*)╯</div>
            <p class="order-message">{t("success-order-text")}</p>
            <Link class="tohome" to="/">{t("success-order-to-home")}</Link>
        </div>
    )
}