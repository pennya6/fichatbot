import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
// RCE CSS
import 'react-chat-elements/dist/main.css';
// MessageBox component
import { MessageBox } from 'react-chat-elements';

import MicIcon from '@mui/icons-material/Mic';
import IconButton  from '@mui/material/IconButton';
import { MessageList } from 'react-chat-elements';
import { Input } from 'react-chat-elements';
import SendIcon from '@mui/icons-material/Send';
import 'react-chat-elements/dist/main.css';

import { useEffect, useState } from "react";
import Chat,{message} from "react-simple-chat";
import Vocal from '@untemps/react-vocal'

const Vchat=({location})=>{

    const [messages,setMessages] = useState([]);
    const [uuid,setUuid]=useState("");

    const [result, setResult] = useState('')

    //함수
	const _onVocalStart = () => {
		setResult('')
	}

	const _onVocalResult = (result) => {
		setResult(result)
	}

    const getAnswer=(message) =>{
        
        setMessages([...messages,message]);
        
        //ajax 짜기
        const url=`http://localhost:8080/chatbot/chat/message`;

        fetch(url,{
            //post,get,delete 등 4가지 메소드
            method:"POST",
            body:JSON.stringify({ //json형태를 string화 하기 위해서
                question:message,uuid:uuid
            
            }), headers:{'Access-Control-Allow-Origin':"*","Content-Type":"application/json"}})
            .then((res) => res.json())
            .then((data) => { 
                setMessages(messages=>[...messages,data]);
            }).catch(()=>{ //오류 잡기
                console.log("에러발생");
            });
    }; 

    const openChat =(message) =>{

        const storeNum=location.props && location.props.num;

        console.log(storeNum)

        const url=`http://localhost:8080/chatbot/chat/open`;

       

        fetch(url,{
            method:"POST", body:JSON.stringify({num:storeNum}),
            headers:{'Access-Control-Allow-Origin':"*","Content-Type":"application/json"}})
            .then((res) =>res.json())
            .then((data)=>{
                console.log(data.uuid)
                setUuid(data.uuid);
                setMessages(messages=>[...messages,data]);
            })
            .catch(()=>{
                console.log("error");
            })

        };
    
    useEffect(openChat,[]);
    //1.변수 선언(상태)

    //2. 함수선언

    //3. html 코드 렌더링


    //항상return
    return(
        <div>
            <Card sx={{height:'96vh', marginTop:'1vh'}}>
                <CardContent style={{backgroundColor:'pink', height:'82vh'}}>
                    <MessageList
                        className='message-list'
                        lockable={true}
                        toBottomHeight={'100%'}
                        dataSource={messages}
                    />
                </CardContent>
                <CardContent>
                <div>
		</div>
                    <Input
                        placeholder="메시지를 입력하시오"
                        multiline={true}
                        defaultValue={result}
                        leftButtons={
                            //마이크사용
                            <Vocal
                                onStart={_onVocalStart}
                                onResult={_onVocalResult}
                                lang="ko-KR"//한국어 설정
                            />
                        }
                        rightButtons={
                            <IconButton aria-label="전송" onClick={()=>getAnswer()}><SendIcon/></IconButton>
                        }
                    />
                </CardContent>
            </Card>
        </div>
        
    );
};

export default Vchat;