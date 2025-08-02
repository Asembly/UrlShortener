'use client'
export function Button({title}: {title: string})
{
    return (
        <button className="
        text-balance
        px-5 w-full cursor-pointer text-center 
        rounded-r-md text-black hover:bg-button-hover bg-button
        max-sm:rounded-md transition-colors duration-300"
        type="submit">
            {title}
        </button>
    )
}