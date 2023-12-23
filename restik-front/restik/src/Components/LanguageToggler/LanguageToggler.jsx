import React, { useState } from "react";
import { useTranslation } from "react-i18next";
import "./LanguageToggler.css";

export const LanguageToggler = () => {
    const [activeLang, setActiveLang] = useState('ru');
    const {i18n} = useTranslation();

    const switchLanguage = (e)=> {
        const lang = e.target.textContent;
        i18n.changeLanguage(lang);
        setActiveLang(lang);
    }
    return (
        <div className="container">
            <div className="lang">
                <button type="submit" className={"ru-lang" + (activeLang === 'ru' ? " active": "")} onClick={switchLanguage}>ru</button>
                <button type="submit" className={"en-lang" + (activeLang === 'en' ? " active": "")} onClick={switchLanguage}>en</button>
            </div>
        </div>
    )
}