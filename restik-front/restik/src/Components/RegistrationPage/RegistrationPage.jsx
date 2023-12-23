import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useTranslation } from "react-i18next";
import "./../LoginPage/LoginPage.css";

export const RegistrationPage = () => {
    const [userData, setUserData] = useState({
        username: '',
        password: '',
        email: '',
    })
    const navigator = useNavigate();
    const { t } = useTranslation();

    const submitHandler = async (e) => {
        e.preventDefault();

        const url = "http://localhost:8080/registration";
        const response = await fetch(url, {
            method: 'post',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userData),
        });
        if (response.status === 200) {
            const user = await response.json();
            localStorage.setItem("@Restik_usin", JSON.stringify(user));
            navigator("/");


        }
    }
    const changeHandle = (e) => {
        const { name, value } = e.target;
        setUserData({ ...userData, [name]: value });
    }
    return (
        <div className="wrapper">
            <div className="wrap">
                <h1 className="title">{t("auth-register-title")}</h1>
                <form method="POST" className="auth-form" onSubmit={submitHandler}>
                    <div className="input-wrap">
                        <label className="user-data">{t("auth-login")}</label>
                        <input className="inp" type="text" name="username" id="username" value={userData.username} autoComplete="off" onChange={changeHandle} />
                    </div>
                    <div className="input-wrap">
                        <label className="user-data">{t("auth-password")}</label>
                        <input className="inp" type="password" name="password" id="password" value={userData.password} onChange={changeHandle} />
                    </div>
                    <div className="input-wrap">
                        <label className="user-data">Email</label>
                        <input className="inp" type="email" name="email" id="email" value={userData.email} autoComplete="off" onChange={changeHandle} />
                    </div>
                    <input type="hidden" name="command" value="REGISTRATE" />
                    <div className="buttons">
                        <input className="loginbtn" type="submit" name="register" value={t("auth-register-btn")} />
                    </div>
                    <a className="regbtn" href="login">{t("auth-signIn-btn")}</a>
                </form>
            </div>

        </div>

    )
}