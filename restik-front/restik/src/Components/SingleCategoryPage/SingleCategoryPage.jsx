import React, { useEffect, useRef, useState } from "react";
import { useParams } from "react-router-dom"
import "./SingleCategoryPage.css";
import { BASE_URL, STORAGE_ITEM } from "../../constants/constants";
import { useTranslation } from "react-i18next";
import { Footer } from "../Footer/Footer";
import { Navbar } from "../Navbar/Navbar";
import checkMark from "../../resource/img/check-mark.png"
import { Loader } from "../Loader/Loader";
import { Notification } from "../Notification/Notification";



export const SingleCategoryPage = (props) => {
    const notifications = useRef();
    const [isAddidng, setIsAdding] = useState(false);
    const [isLoading, setIsLoading] = useState(true);
    const [dishes, setDishes] = useState([]);
    const [notificationsArr, setNotificationsArr] = useState([]);
    const [dishId, setDishId] = useState(-1);
    const { category_id } = useParams();
    const user = JSON.parse(localStorage.getItem(STORAGE_ITEM));
    const { t } = useTranslation();


    useEffect(() => {
        const getDishesInCategory = async () => {
            let url = BASE_URL + "single-category/" + category_id;
            let response = await fetch(url);
            const dishesInCategory = await response.json();
            setIsLoading(false);
            setDishes(dishesInCategory);
            return dishesInCategory
        }

        const scrollHandler = (e) => {
            const scrollY = window.scrollY;
            const delta = 100 - scrollY;
            if (delta < 20) {
                notifications.current.style.top = "20px";
            } else {
                notifications.current.style.top = delta + "px";
            }
        }

        // notification.current.classList.add("hidden");
        window.scrollTo(0, 0);
        window.addEventListener("scroll", scrollHandler)
        try {
            getDishesInCategory();

        } catch (error) {
            console.log(error.message);
        }
        return () => window.removeEventListener("scroll", scrollHandler);
    }, [])

    const addToBasket = async (url) => {
        const getDishIdFromUrl = (url = "") => {
            const start = url.indexOf("category/") + "category/".length;
            const end = url.indexOf("/", start);
            return +url.slice(start, end)
        }
        try {
            setIsAdding(true);
            setDishId(getDishIdFromUrl(url));
            const response = await fetch(url, {
                method: "post",
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(user),
            });

            if (response.status === 200) {
                setTimeout(() => {
                    setIsAdding(false);
                    setNotificationsArr((prevNotifications)=> [{
                        id: Math.random().toString(),
                    }, ...prevNotifications, ])
                }, 500)

            }
        } catch (error) {
            console.log(error.message);
        }
    }

    const deleteNotification = (id)=> {
        setNotificationsArr((prevNotifications) => prevNotifications.filter((notification) => notification.id !== id));
    }
    return (
        <>
            <Navbar />
            <div className="notifications" ref={notifications}>
                {
                    notificationsArr.map((notification)=> <Notification key={notification.id} id={notification.id} deleteNotification={deleteNotification}/>)
                }
            </div>

            <section className="single-category">
                <div className="container">

                    <h2 className="single-category-title">{t("single-cat-dishes")}</h2>
                    <div className="dishes">

                        {isLoading ? <Loader isBig={true} /> :

                            dishes.map((dish, ind) => {
                                return (
                                    <div className='dish-item' key={ind}>
                                        <div className="img-and-btn">
                                            <div className='dish-item-img'>
                                                <img src={dish.img} alt={dish.name} />
                                                <span className='dish-item-price'>{dish.price} {t("currency")}</span>
                                            </div>
                                            <button className='tobasket' disabled={user ? false : true} onClick={() => addToBasket(`http://localhost:8080/single-category/${dish.dish_id}/${category_id}/add-to-basket`)}>
                                                <p>{t("single-cat-add-to-basket")} </p>
                                                {isAddidng && dishId === dish.dish_id ? <Loader isBig={false} /> : null}
                                            </button>
                                        </div>

                                        <div className='dish-item-info'>
                                            <h3 className='dish-item-title'>{dish.name}</h3>
                                            <p className='dish-item-description'>
                                                {dish.description}
                                            </p>
                                        </div>
                                    </div>
                                )
                            })
                        }
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}