export interface LocalDescarteConsultaDTO {
  id: number;
  titulo: string;
  descricao: string;
  logradouro: string;
  numero: number;
  complemento: string;
  referencia: string;
  bairro: string;
  cidade: string;
  uf: string;
  tiposResiduo: string[];
}
