import i18n from 'i18next';
import Backend from 'i18next-http-backend';
import LanguageDetector from 'i18next-browser-languagedetector';
import { initReactI18next } from 'react-i18next';
import translationEN from "./locales/en/translation.json";
import translationRU from "./locales/ru/translation.json";

const resources = {
  en: {
    translation: translationEN
  },
  ru: {
    translation: translationRU
  }
};


i18n.use(Backend).use(LanguageDetector).use(initReactI18next).init({
    fallbackLng: 'ru',
    whitelist: ['ru', 'en'],
    debug : true,
    detection:{
        order: ['localStorage', 'cookie'],
        cache: ['localStorage', 'cookie']
    },
    interpolation: {
        escapeValue: false
    },
    resources
})

export default i18n;