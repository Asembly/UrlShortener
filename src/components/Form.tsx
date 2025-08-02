'use client'
import {createShortUrl} from "@/app/server_actions"
import {Button} from "@/components/Button"
import {Input} from "@/components/Input"
import { useActionState} from "react"
import { CopyButton } from "./CopyButton"
import { getBaseUrl } from "@/app/actions"

const initState = {
  message: ''
}



export function Form()
{

    const [state, formAction] = useActionState(createShortUrl, initState) 
    const baseUrl = getBaseUrl()

    return(
        <div className='mt-40 container flex justify-center m-auto'> 
            <div>
                <div>
                    <form action={formAction} className="flex max-sm:flex-col"> 
                        <Input></Input>
                        <Button title="short"></Button>
                    </form>
                </div>
                <div className="flex justify-center py-5">
                    <div>
                    {
                        state.errors && 
                        <div className='text-red-600 font-bold'>
                            {state.errors}
                        </div>
                    }
                    </div>
                    <div> 
                    {
                        state.shortUrl &&
                        <div>
                            <span className="black text-xl" id="copy">
                                {state.shortUrl}
                            </span>
                            <CopyButton copyText={`${baseUrl + state.shortUrl}`}></CopyButton>
                        </div>
                    }
                    </div>    
                </div>
            </div>
        </div>
    )
}