import React from "react";
interface CardProps{
    children: React.ReactNode;
    className?: String;
}

export const Card: React.FC<CardProps> = ({children, className}) =>{
    return (
        <div className={`bg-white rounded-2xl shadow-md p-6 ${className}`}>
            {children}
        </div>
    );

}