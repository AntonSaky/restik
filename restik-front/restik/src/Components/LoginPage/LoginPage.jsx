import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useTranslation } from "react-i18next";
import "./LoginPage.css";


//Для входа в кабинент админа login: admin, password: admin
export const LoginPage = () => {
    const [userData, setUserData] = useState({
        username: '',
        password: '',
    })
    const navigator = useNavigate();
    const {t} = useTranslation();

    const submitHandler = async (e)=> {
        e.preventDefault();

        const url = "http://localhost:8080/signIn";
        const response = await fetch(url, {
            method: 'post',
            headers:{ 'Content-Type': 'application/json'},
            body: JSON.stringify(userData),
        });
        if(response.status === 200){
            const user = await response.json();
            if(user.role === 'USER'){
                localStorage.setItem("@Restik_usin", JSON.stringify(user));
                navigator("/");
            }else{
                navigator("/admin");
            }
        }
    }
    const changeHandle = (e)=>{
        const {name, value} = e.target;
        setUserData({...userData, [name]: value});
    }

    return (
        <div className="wrapper">
            <div className="wrap">
                <h1 class="title">{t("auth-signIn-title")}</h1>
                <form method="POST" className="auth-form" onSubmit={submitHandler}>
                    <div className="input-wrap">
                        <label className="user-data">{t("auth-login")}</label>
                        <input className="inp" type="text" name="username" id="username" value={userData.username} autoComplete="off" onChange={changeHandle}/>
                    </div>
                    <div className="input-wrap">
                        <label className="user-data">{t("auth-password")}</label>
                        <input className="inp" type="password" name="password" id="password" value={userData.password} onChange={changeHandle}/>
                    </div>
                    <div className="buttons">
                        <input className="loginbtn" type="submit" name="login" value={t("auth-signIn-btn")} />
                    </div>
                    <a className="regbtn" href="/registration">{t("auth-register-btn")}</a>
                </form>
            </div>
        </div>
    )
}