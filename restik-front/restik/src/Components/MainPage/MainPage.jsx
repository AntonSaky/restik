import React, { useEffect, useRef } from "react";
import "./MainPage.css"
import { Link } from "react-router-dom";
import { LanguageToggler } from "../LanguageToggler/LanguageToggler";
import { useTranslation } from "react-i18next";
import { Navbar } from "../Navbar/Navbar";
import { Footer } from "../Footer/Footer";

export const MainPage = () => {
    const { t } = useTranslation();
    const menuSection = useRef(null);
    useEffect(()=> {
        if(window.location.hash === "#menu"){
            if(menuSection.current !== null){
                menuSection.current.scrollIntoView({behavior: "smooth"});
            }
        }
    }, [])
    return (
        <>
            <Navbar elementToScroll={menuSection} />
            <LanguageToggler />
            <section className="main-screen">
                <div className="container">
                    <div className="main-screen-img">
                    </div>
                </div>
            </section>
            <section id="menu" className="menu-categories" ref={menuSection}>
                <div className="container">
                    <h2 className="menu-categories-title">{t("main-menu")}</h2>
                    <div className="menu-items">
                        <Link className="menu-category-btn" to="single-category/1">
                            <div className="menu-item">
                                <div className="menu-category-img" id="breakfast">
                                    <span className="menu-category-title">{t("main-breakfast")}</span>
                                </div>
                            </div>
                        </Link>

                        <Link className="menu-category-btn" to="single-category/2">
                            <div className="menu-item">
                                <div className="menu-category-img" id="soup">
                                    <span className="menu-category-title">{t("main-soup")}</span>
                                </div>
                            </div>
                        </Link>

                        <Link className="menu-category-btn" to="single-category/3">
                            <div className="menu-item">
                                <div className="menu-category-img" id="deserts">
                                    <span className="menu-category-title">{t("main-deserts")}</span>
                                </div>
                            </div>
                        </Link>

                        <Link className="menu-category-btn" to="single-category/4">
                            <div className="menu-item">
                                <div className="menu-category-img" id="street-food">
                                    <span className="menu-category-title">{t("main-streetfood")}</span>
                                </div>
                            </div>
                        </Link>
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}