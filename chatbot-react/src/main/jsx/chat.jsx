import { useEffect, useState } from "react";
import Chat,{message} from "react-simple-chat";
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

    //처음 message 상태
    //id값을 통해서 송신자, 수신자 판별
    //변수 선언
     const [messages,setMessages] = useState([
        //{
            // id : 'chatbot',
    //         //text: '안녕하세요 chatbot입니다.',
            // createdAt : new Date(),
            // user : {id:'user'}
         //}
     ]);
     const [uuid,setUuid]=useState("");

    //응답 만들기
    //CORS : 도메인 또는 포트가 다른 서버의 자원을 요청하는 메커니즘
    //함수선언
    const getAnswer=(message) =>{
        //console.log("HI");

        //처음 질문도 저장하기 위해서
        setMessages([...messages,message]);
        
        //ajax 짜기
        const url=`http://localhost:8089/chatbot/chat/message`;

        //Access-Control-Allow-Origin response 헤더를 추가
        
        //자바스크립트를 사용하기 위해서 fetch사용
        //fetch함수 기본 틀
        //method가 post인 경우
        //fetch()기본은 get이기 때문에 post인 경우에는 method정보를 인자로 넘겨주어야한다
        fetch(url,{
            //post,get,delete 등 4가지 메소드
            method:"POST",
            body:JSON.stringify({ //json형태를 string화 하기 위해서
                question:message,uuid:uuid
                //headers부분에 CORS문제 해결
            }), headers:{'Access-Control-Allow-Origin':"*","Content-Type":"application/json"}})
            //보내고 나서 처리를 어떻게 할 것 인가?
            //송신후에 받아와서 json형태로 만든다
            .then((res) => res.json())
            //다시 데이터로 받아서 
            .then((data) => {
                //메세지안에 저장하겠다
                //답변 
                setMessages(messages=>[...messages,data]);
            }).catch(()=>{ //오류 잡기
                console.log("에러발생");
            });
    }; 

    const openChat =(message) =>{
        //fetch 메소드 구현하기
        console.log("openChat");

        //setMessages([...messages,message]);
        const url=`http://localhost:8089/chatbot/chat/open`;

        fetch(url,{
            method:"POST",body:JSON.stringify({ //json형태를 string화 하기 위해서
                data:messages
                //headers부분에 CORS문제 해결
            }),
            headers:{'Access-Control-Allow-Origin':"*","Content-Type":"application/json"}})
            .then((res) =>res.json())
            .then((data)=>{
                console.log(data.uuid)
                setUuid(data.uuid);
                setMessages(messages=>[...messages,data]);
            })
            .catch(()=>{
                console.log("error");
            });

        };
    

    //첫화면 렌더링
    //상태값을 바뀌면 호출
    //처음들어오면 오픈챗 메소드를 탐
    useEffect(openChat,[]);

    return(
        <Chat
            title="pennya6 Chatbot"
            user={{id : 'chatbot'}}
            messages={messages}
            //()=> 함수 표시 필수
            onSend={(question)=>getAnswer(question)}
        />
    );
};

export default Messenger;
