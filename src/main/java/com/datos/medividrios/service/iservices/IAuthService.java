package com.datos.medividrios.service.iservices;

import com.datos.medividrios.dto.autenticacion.AuthRequest;
import com.datos.medividrios.dto.autenticacion.AuthResponse;

public interface IAuthService {
    AuthResponse login(AuthRequest request);
}
