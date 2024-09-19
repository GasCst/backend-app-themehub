----------------------------------
--fn_pre_format_cadena function
----------------------------------

create or replace function dbo.fn_pre_format_cadena(
in ref_msg text
)returns text
as 
$$
declare 
 r_a text;
 r_e text;
 r_i text;
 r_o text; 
 r_u text;
begin
 r_a:=replace(lower(ref_msg),chr(225),chr(97));
 r_e:=replace(lower(r_a),chr(233),chr(101));
 r_i:=replace(lower(r_e),chr(237),chr(105));
 r_o:=replace(lower(r_i),chr(243),chr(111));
 r_u:=replace(lower(r_o),chr(250),chr(117));
 return r_u;
end;
$$
language 'plpgsql';