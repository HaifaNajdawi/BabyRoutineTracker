import React from "react";

interface CardHeaderProps{
    children:React.ReactNode;
}

export const CardHeader:React.FC<CardHeaderProps>=({children}) =>{
    return(
        <div className="mb-4">{children}</div>
    );

};