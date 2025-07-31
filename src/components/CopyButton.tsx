import { useState } from "react"

export function CopyButton({copyText}: {copyText: string})
{
    const [text, setText] = useState("копировать")

    return (
        <button className="
            px-0.5 w-full cursor-pointer text-center 
            font-[650] bg-white rounded-md text-black 
            hover:bg-amber-100 transition-colors duration-300"
            type="submit" onClick={() => {
                navigator.clipboard.writeText(copyText)
                setText("скопировано!")
                }}>
                {text}
        </button>
    )
}