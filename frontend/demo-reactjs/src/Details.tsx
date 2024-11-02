import React from "react";


interface DetailsProps {
    names: string[];
    title: number;
};


const Details = (props: DetailsProps)  => {
    const { names } = props;
    return (
        
          <div>
            <h1>Details</h1>

            <ul>
          {names.map((name, index) => (
            <li key={index}>{name}</li>
          ))}
        </ul>

        </div>

     
      
        
    );
};

export default Details;