import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: Http) { }

  login(username: string, password: string) {
      let credentials = new URLSearchParams();
      credentials.append('username', username);
      credentials.append('password', password);
      return this.http.post('/users/authenticate', credentials)
        .map((response: Response) => {
          let user = response.json();
          if (user) {
            localStorage.setItem('currentUser', JSON.stringify(user));
          }
        });
    }
}