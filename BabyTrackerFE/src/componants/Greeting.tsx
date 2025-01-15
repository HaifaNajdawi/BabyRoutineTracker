import React from 'react';


interface GreetingProps {
    name: string;
  }

export const Greeting:React.FC<GreetingProps>= ({name})=>{
    return <h4> Hello, {name}</h4>
}