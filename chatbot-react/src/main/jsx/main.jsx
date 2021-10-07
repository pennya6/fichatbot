
import * as React from 'react';
import Button from '@mui/material/Button';
import { useHistory } from "react-router-dom";

const Main=()=>{

   const history=useHistory();
    //함수
    const selectStore=(num)=>{
        history.push({
            pathname: "/vchat",
            props: { num:num }
        });
    };

    //항상return
    return(
        //html 코드
        <div className="main">
            <h1 style={{fontSize:'8em'}}>pennya6 pizza</h1>
                {/* <Link to="/chat">
                    <button>채팅화면으로 이동</button>
                </Link> */}
            <Button variant="contained" onClick={()=>selectStore(1)}>서울지점</Button>
            <Button variant="contained" onClick={()=>selectStore(2)}>경기지점</Button>
        </div>
        
    );
};

export default Main;