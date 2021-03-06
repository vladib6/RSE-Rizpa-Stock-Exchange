
import { Link } from "react-router-dom";
import { Contact } from "./Contact";


export const Homepage=()=>{
    
   
    return (
      <>
      <header className="masthead" style={{background: 'url(/Web-App_Web/assets/img/stocks.jpg)'}}>
        <div className="container">
            <div className="intro-text">
                <div className="intro-lead-in">Welcome To Rizpa-Stock-Exchange!</div>
                <div className="intro-heading text-uppercase">The best stock trading system in the world!</div>
                <Link className="btn btn-primary btn-xl text-uppercase" to="/signup" >get started</Link>
            </div>
        </div>
    </header>
    <section className="bg-light" id="team">
        <div className="container">
            <div className="row">
                <div className="col">
                    <div className="team-member"><img alt="vladi" className="rounded-circle mx-auto" src="/Web-App_Web/assets/img/team/vladi.png"/>
                        <h4>Vladi Binyaminov</h4>
                        <p className="text-muted">Web Developer</p>
                        <ul className="list-inline social-buttons">
                            <li className="list-inline-item"><a href="https://github.com/vladib6"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-github" viewBox="0 0 16 16">
  <path d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.012 8.012 0 0 0 16 8c0-4.42-3.58-8-8-8z"/>
</svg></a></li>
                            <li className="list-inline-item"><a href="https://www.facebook.com/vladi.binyaminov/"><i className="fa fa-facebook"></i></a></li>
                            <li className="list-inline-item"><a href="https://www.linkedin.com/in/vladi-binyaminov/"><i className="fa fa-linkedin"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div className="col">
                    <div className="team-member"><img alt="shalev" className="rounded-circle mx-auto" src="/Web-App_Web/assets/img/team/shalev.png"/>
                        <h4>Shalev Sharf</h4>
                        <p className="text-muted">Web Developer</p>
                        <ul className="list-inline social-buttons">
                            <li className="list-inline-item"><a href="https://github.com/shalevsh"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" className="bi bi-github" viewBox="0 0 16 16">
  <path d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.012 8.012 0 0 0 16 8c0-4.42-3.58-8-8-8z"/>
</svg></a></li>
                            <li className="list-inline-item"><a href="https://www.facebook.com/shalev.sharf/"><i className="fa fa-facebook"></i></a></li>
                            <li className="list-inline-item"><a href="https://www.linkedin.com/in/shalev-sharf-629a401ba/"><i className="fa fa-linkedin"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <Contact/>
   </>
    )
  }

    

