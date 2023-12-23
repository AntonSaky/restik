import React, { useEffect, useRef, useState } from "react";
import "./Notification.css";
import { useTranslation } from "react-i18next";
import checkMark from "../../resource/img/check-mark.png";

export const Notification = ({ id, deleteNotification }) => {
    const { t } = useTranslation();
    useEffect(() => {
        setTimeout(() => {
            deleteNotification(id);
        }, 4000)
        return () => {
            console.log("deleted");
        }
    }, [])
    return (
        <>
            <div className="to-basket-notification">
                <div div className="notification-img" >
                    <img src={checkMark} alt="check-mark" />
                </div >
                <div className="notification-info">
                    <h3 className="notification-title">{t("notification-title")}</h3>
                    <p className="notification-message">{t("notification-message")}</p>
                </div>
            </div >
        </>
    )
}