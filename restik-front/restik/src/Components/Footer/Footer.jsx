import React from "react";
import "./Footer.css"

export const Footer = () => {
    return (
        <footer>
            <div className="container">
                <h3 className="footer-title">Наше расположение</h3>
                <div className="map">
                    <a className="map-link" href="https://goo.gl/maps/JMnpTyQd8CUJm8pz7" target="_blank">
                        <div className="map-item">
                            <iframe width="100%"
                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2350.1197435668223!2d27.59273715106426!3d53.911848039805776!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbcfaefc7ace77%3A0x108d2d965ec4df2f!2z0YPQuy4g0JPQuNC60LDQu9C-IDksINCc0LjQvdGB0Lo!5e0!3m2!1sru!2sby!4v1677136102096!5m2!1sru!2sby"
                                allowFullScreen="" loading="lazy"
                                referrerPolicy="no-referrer-when-downgrade">
                            </iframe>
                            <span className="map-title">map</span>
                        </div>
                    </a>
                </div>
                <div className="copyright">
                    All rights are reserved &#169;
                </div>
            </div>
        </footer>
    )
}