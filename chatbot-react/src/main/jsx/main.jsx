import { Link } from "react-router-dom";

const Main=()=>{
    //항상return
    return(
        //html 코드
        <div>
            <h1>메인화면</h1>
                <Link to="/chat">
                    <button>ChatBot</button>
                </Link>

        </div>
    );
};

//jsx구조
export default Main;