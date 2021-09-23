import { Link } from "react-router-dom";

const Main=()=>{
    return(
        <div>
            <h1>메인화면</h1>
                <Link to="/chat">
                    <button>ChatBot</button>
                </Link>

        </div>
    );
};

export default Main;