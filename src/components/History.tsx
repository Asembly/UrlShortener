'use server'
import { getUrls } from "@/app/server_actions"
import { CopyButton } from "@/components/CopyButton"
import { Url } from "@/app/types"
import { getBaseUrl } from "@/app/actions"



export async function History()
{

    const urls:Url[] = await getUrls() 
    const baseUrl = getBaseUrl() 

    return(
        <div>
            <div className="flex justify-center w-100 max-sm:w-70 bg-black/5 m-auto rounded-2xl">
                <div className='space-y-3 py-5 max-h-130 overflow-y-scroll whitespace-nowrap scrollbar'>
                    {
                        urls.map(item => (
                            <div key={item.id} className="flex items-center space-x-2">
                                <div>
                                    {baseUrl+item.shortUrl}
                                </div>
                                <div>
                                    <CopyButton copyText={`${baseUrl+item.shortUrl}`}></CopyButton>
                                </div>
                            </div>        
                        ))
                    }
                </div>
            </div>
        </div>
    )
}