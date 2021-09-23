import { useState } from "react";
import Chat from "react-simple-chat";
import 'react-simple-chat/src/components/index.css';

//react에서 api를 사용하는 방법으로 크게 Axios, Fetch api가 있다

//fetch() 함수 기본 틀
//fetch()
//.then(res=>res.json())
//.then(res=>{
//   data를 응답 받은 후의 로직 
//});
//

const Messenger = () =>{

    //state란 -> 컴포넌트 내부에서 변경될 수 있는 값 
    //함수형 컴포넌트가 useState라는 함수로 사용

    //첫번째 파라미터는 '현재상태', 두번째는 '현재 상태를 바꿔주는 함수'
    //setMessages는 message(현재상태)를 바꿔줄 함수
    const [messages,setMessages] = useState([
        {
            id : 'chatbot',
            text: '안녕하세요 chatbot입니다.',
            createdAt : new Date(),
            user : {id:'user'}
        }
    ]);

    //CORS : 도메인 또는 포트가 다른 서버의 자원을 요청하는 메커니즘
    const getAnswer=(message) =>{
        setMessages([...messages,messages]);
        
        //api주소
        const url='http://localhost:8080/main/chat';

        //Access-Control-Allow-Origin response 헤더를 추가
        
        //fetch함수 기본 틀
        //method가 post인 경우
        //fetch()기본은 get이기 때문에 post인 경우에는 method정보를 인자로 넘겨주어야한다
        fetch(url,{
            method:"POST",
            body:JSON.stringify({
                data:messages
                //headers부분에 CORS문제 해결
            }), headers:{'Content-Type':'application/json'}})
            .then((res) => res.json())
            .then((data) => {
                setMessages("성공");
            }).catch(()=>{
                console.log("에러발생");
            });
    }; 

    return(
        <Chat
            title="pennya6 Chatbot"
            user={{id : 'chatbot'}}
            messages={messages}
            onSend={messages=>setMessages([messages])}
        />
    );
};

export default Messenger;
