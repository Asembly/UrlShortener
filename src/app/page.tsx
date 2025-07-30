'use client'
import Form from 'next/form'
import { useActionState, useState } from 'react';
import createShortUrl from './actions';

const initState = {
  message: ''
}

export default function Home() {

  const [state, formAction] = useActionState(createShortUrl, initState) 

  console.log(state)

  return (
    <div> 
      <form action={formAction}> 
        <input type="text" name='longUrl' placeholder='type any text'/>
        <button type="submit">send</button>
      </form> 
      <div>
        {
          state.errors && 
          <div>
            {state.errors}
          </div>
        }
      </div>
      <div>
        {
          state.shortUrl &&
          <div>
            http://localhost:3000/{state.shortUrl}
          </div>
        }
      </div>
    </div>
  );
}
