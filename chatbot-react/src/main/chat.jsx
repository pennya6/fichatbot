import { useState } from "react";
import Chat from "react-simple-chat";
import 'react-simple-chat/src/components/index.css';

const Messenger = () =>{

    //state란 -> 컴포넌트 내부에서 변경될 수 있는 값 
    //함수형 컴포넌트가 useState라는 함수로 사용

    //첫번째 파라미터는 '현재상태', 두번째는 '현재 상태를 바꿔주는 함수'
    //setMessages는 message(현재상태)를 바꿔줄 함수
    const [messages,setMessages] = useState([
        {
            id : 'chatbot',
            text: '안녕하세요 궁금한 것을 물어보세요.',
            createdAt : new Date(),
            user : {id:'user'}
        }
    ]);

   /*  //CORS : 도메인 또는 포트가 다른 서버의 자원을 요청하는 메커니즘
    const getAnswer=(message) =>{
        setMessages([...messages,messages]);
        const url='http://localhost:8080/main/chat';

        //Access-Control-Allow-Origin response 헤더를 추가
        
        fetch(url,{method:"POST",body:JSON.stringify({data:messages}), headers:{'Content-Type':'application/json'})
        .then((res) => res.json())
        .then((data) => {
            setMessages("성공");
        }).catch(()=>{
            console.log("에러발생");
        });
    }; */

    return(
        <Chat
            title="챗봇 샘플"
            user={{id : 'chatbot'}}
            messages={messages}
            onSend={messages=>setMessages([...messages,messages])}
        />
    );
};

export default Messenger;
