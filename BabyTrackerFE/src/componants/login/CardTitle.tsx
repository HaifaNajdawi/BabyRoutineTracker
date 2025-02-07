import React from "react";
interface CardTitleProps {
    className?: String;
    children:React.ReactNode;
}

export const CardTitle:React.FC<CardTitleProps> =({className,children})=>{
    return(
        <h2 className={`text-xl font-semibold ${className}`}>{children}</h2>
    );
};