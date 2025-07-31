'use client'

import createShortUrl from "@/app/actions"
import {Button} from "@/components/Button"
import {Input} from "@/components/Input"
import { useActionState } from "react"
import { CopyButton } from "./CopyButton"

const initState = {
  message: ''
}

export function Form()
{

    const [state, formAction] = useActionState(createShortUrl, initState) 

    return(
        <div className='flex justify-center items-center mt-40'> 
            <div>
                <div>
                <form action={formAction} className="flex space-x-5"> 
                    <Input></Input>
                    <Button title="сократить"></Button>
                </form>
                </div>
                <div className="flex justify-center py-5">
                    <div>
                    {
                        state.errors && 
                        <div className='text-red-300'>
                            {state.errors}
                        </div>
                    }
                    </div>
                    <div>
                    {
                        state.shortUrl &&
                        <div>
                            <span className="text-amber-200" id="copy">
                                http://localhost:3000/{state.shortUrl}
                            </span>
                            <CopyButton copyText={`http://localhost:3000/${state.shortUrl}`}></CopyButton>
                        </div>
                    }
                    </div>    
                </div>
            </div>
        </div>
    )
}