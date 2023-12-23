import React from "react";
import { Bars, Puff } from "react-loader-spinner";
import "./Loader.css";

export const Loader = ({isBig = true}) => {

    return (
        isBig ? 
        <div className="loader">
            <Bars
                height="80"
                width="80"
                color="#f6b319"
                ariaLabel="bars-loading"
                wrapperStyle={{}}
                wrapperClass=""
                visible={true}
            />
        </div> :
        <Puff
        visible={true}
        height="16"
        width="16"
        color="#f5bb3a"
        ariaLabel="puff-loading"
        wrapperStyle={{}}
        wrapperClass=""
        />


    )
}