import React, { useEffect, useState } from "react";
import "./AdminPanel.css"

export const AdminPanel = () => {
    const [isLoading, setIsLoading] = useState(true);
    const [usersInfo, setUsersInfo] = useState([]);
    
    useEffect(() => {
        const fetchData = async () =>{
            let url = "http://localhost:8080/admin";
            let response = await fetch(url);
            const userInfoArr = await response.json();
            console.log(userInfoArr);
            setIsLoading(false);
            setUsersInfo(userInfoArr);
        }
        try {
            fetchData();
        } catch (error) {
            console.log(error.message);
        }
        


    }, []);
    return (
        <div className="admin-wrapper">
            <h2 className="admin-title">Статистика</h2>
            {isLoading ? "Подождите, идет загрузка..." :
                <ol>
                    {
                        usersInfo.map(userInfo => {
                            return (
                                <li>
                                    <br />
                                    <ul>
                                        <li>Логин: {userInfo.name}</li>
                                        <li>Email: {userInfo.email}</li>
                                        <li>Password: {userInfo.password}</li>
                                        <li>Товары в корзине:
                                            <ul>
                                                {
                                                    userInfo.dishesInBasket.length !== 0 ?
                                                        userInfo.dishesInBasket.map(dishinfo => {
                                                            console.log(dishinfo);

                                                            return (
                                                                <li>{dishinfo.dish.name}({dishinfo.amount}шт)</li>
                                                            )
                                                        })
                                                        :
                                                        <li>Корзина пользователя {userInfo.name} пустая</li>
                                                }                                             
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                            )
                        })
                    }
                </ol>
            }

        </div >
    )
}