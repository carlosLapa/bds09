import { ReactComponent as AuthImage } from 'assets/images/auth-image.svg';

const Auth = () => {
  return (
    <div className="auth-container">
      <div className="auth-banner-container">
        <h1>Avalie Filmes</h1>
        <p>Diga o que você achou do seu filme favorito</p>
        <AuthImage />
      </div>
      <div className="auth-form-container">

      </div>
    </div>
  );
};

export default Auth;
